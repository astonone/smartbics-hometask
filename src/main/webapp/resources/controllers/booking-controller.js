var app = angular.module('booking-service', []);
app.controller('BookingController', function ($scope, $http) {

    $scope.serverError;
    $scope.uncorrectlyDataError;
    $scope.requestError;
    $scope.getListBookingsRequestError;
    $scope.isNotEmpty = false;
    $scope.existsBookingsByDate = "";
    $scope.bookingsData = [];
    $scope.bookingsRequestData = "";
    $scope.isRequested = false;
    $scope.isBooked = false;
    $scope.isNOData = false;
    $scope.successfullBookingsData = [];
    $scope.dataForBooking = {};

    $scope.getBookingsByDate = function () {
        let dateArr = $scope.existsBookingsByDate.split("/");
        let url = "booking/findByDate?year=" + dateArr[0] + "&month=" + dateArr[1] + "&day=" + dateArr[2];
        $http.get(url)
            .success(function (data, status, headers, config) {
                $scope.bookingsData = data.bookingsDTOList;
                $scope.parseBookingData($scope.bookingsData);
                $scope.bookingsData.length === 0 ? $scope.isNotEmpty = false : $scope.isNotEmpty = true;
                $scope.getListBookingsRequestError = false;
            })
            .error(function (data, status, headers, config) {
                $scope.isNotEmpty = false;
                $scope.getListBookingsRequestError = true;
            });

    };

    $scope.parseBookingData = function (bookingData) {
        for (let i = 0; i < bookingData.length; i++) {
            let element = bookingData[i];
            let companyWorkTimeParsed = element.companyWorkTime;
            companyWorkTimeParsed = $scope.insert(companyWorkTimeParsed, ":", 2);
            companyWorkTimeParsed = $scope.insert(companyWorkTimeParsed, " -", 5);
            companyWorkTimeParsed = $scope.insert(companyWorkTimeParsed, ":", 10);
            element.companyWorkTime = companyWorkTimeParsed;
            let dateRequest = {
                year: element.requestDate.year,
                month: element.requestDate.month,
                day: element.requestDate.day,
                hours: element.requestDate.hours,
                minutes: element.requestDate.minutes,
                seconds: element.requestDate.seconds
            };
            let requestDateView = dateRequest.year + "/" + dateRequest.month + "/" + dateRequest.day + " " +
                dateRequest.hours + ":" + dateRequest.minutes + ":" + dateRequest.seconds;
            element.requestDate = requestDateView;
            let bookingDate = {
                year: element.bookingDate.year,
                month: element.bookingDate.month,
                day: element.bookingDate.day,
                hours: element.bookingDate.hours,
                minutes: element.bookingDate.minutes,
                seconds: element.bookingDate.seconds
            };
            let bookingDateView = bookingDate.year + "/" + bookingDate.month + "/" + bookingDate.day + " " +
                bookingDate.hours + ":" + bookingDate.minutes + ":" + bookingDate.seconds;
            element.bookingDate = bookingDateView;
            let bookingTimeView = bookingDate.hours + ":" + bookingDate.minutes + " - " + (bookingDate.hours + element.bookingTime) + ":" + bookingDate.minutes;
            element.bookingTime = bookingTimeView;
        }
    };

    $scope.insert = function (str, str1, n) {
        return (!str1 ) ? str : (!n) ? str1 + str : str.slice(0, n) + str1 + str.slice(n);
    };

    $scope.sendRequestForBooking = function () {
        let parseArr = $scope.bookingsRequestData.split("\n");
        let sendData = $scope.preparePutObjects(parseArr);
        $scope.isBooked = false;
        try {
            $scope.checkData(sendData);
            $http.put('booking/request', sendData).success(function (data, status, headers, config) {
                $scope.post = data;
                if ($scope.post.bookingsDTOList.length === 0) {
                    $scope.isNOData = true;
                    $scope.uncorrectlyDataError = false;
                    return;
                } else {
                    $scope.isNOData = false;
                    $scope.dataForBooking = $scope.prepareSuccessfullBookingData(data.bookingsDTOList);
                    $scope.parseBookingData($scope.post.bookingsDTOList);
                    $scope.successfullBookingsData = $scope.post.bookingsDTOList;
                    $scope.isRequested = true;
                    $scope.uncorrectlyDataError = false;
                    $scope.requestError = false;
                    $scope.getListBookingsRequestError = false;
                }

            }).error(function (data, status, headers, config) {
                $scope.requestError = true;
            });
        } catch (err) {
            $scope.uncorrectlyDataError = true;
            $scope.requestError = false;
            $scope.isNOData = false;
        }

    };

    $scope.makeBooking = function () {
        let sendData = $scope.dataForBooking;
        $http.put('booking/create', sendData).success(function (data, status, headers, config) {
            $scope.isRequested = false;
            $scope.isBooked = true;
            $scope.uncorrectlyDataError = false;
        }).error(function (data, status, headers, config) {
            $scope.serverError = true;
        });
    };

    $scope.prepareSuccessfullBookingData = function (bookingList) {
        let copy = JSON.parse(JSON.stringify(bookingList));
        copy = {bookingsDTOList: copy};
        return copy;
    };

    $scope.preparePutObjects = function (parseArr) {
        let workTimes = parseArr[0];
        let result = [];
        for (let i = 1; i < parseArr.length - 1; i += 2) {
            let dateAndEmployee = parseArr[i];
            let bookingDetails = parseArr[i + 1];
            let dateAndEmployeeParseArr = dateAndEmployee.split(" ");
            let date = dateAndEmployeeParseArr[0].split("-");
            let time = dateAndEmployeeParseArr[1].split(":");
            let employee = dateAndEmployeeParseArr[2];
            let bookingDetailsParseArr = bookingDetails.split(" ");
            let dateBooking = bookingDetailsParseArr[0].split("-");
            let timeBooking = bookingDetailsParseArr[1].split(":");
            let bookingHours = bookingDetailsParseArr[2];
            let entity = {
                companyWorkTime: workTimes,
                requestDate: {
                    year: date[0],
                    month: date[1],
                    day: date[2],
                    hours: time[0],
                    minutes: time[1],
                    seconds: time[2]
                },
                employee: employee,
                bookingDate:
                    {
                        year: dateBooking[0],
                        month: dateBooking[1],
                        day: dateBooking[2],
                        hours: timeBooking[0],
                        minutes: timeBooking[1],
                        seconds: 0
                    },
                bookingTime: bookingHours
            };
            result.push(entity);
        }
        return {companyWorkTime: workTimes, bookingsList: result};
    };

    $scope.checkData = function (entity) {
        for (let i = 0; i < entity.bookingsList.length; i++) {
            if (entity.companyWorkTime === undefined ||
                entity.bookingsList[i].employee === undefined ||
                entity.bookingsList[i].bookingTime === undefined ||
                entity.bookingsList[i].requestDate.year === undefined ||
                entity.bookingsList[i].requestDate.month === undefined ||
                entity.bookingsList[i].requestDate.day === undefined ||
                entity.bookingsList[i].requestDate.hours === undefined ||
                entity.bookingsList[i].requestDate.minutes === undefined ||
                entity.bookingsList[i].requestDate.seconds === undefined ||
                entity.bookingsList[i].bookingDate.year === undefined ||
                entity.bookingsList[i].bookingDate.month === undefined ||
                entity.bookingsList[i].bookingDate.day === undefined ||
                entity.bookingsList[i].bookingDate.hours === undefined ||
                entity.bookingsList[i].bookingDate.minutes === undefined ||
                entity.bookingsList[i].bookingDate.seconds === undefined) {
                throw new SyntaxError("Данные некорректны");
            }
        }

    };

});