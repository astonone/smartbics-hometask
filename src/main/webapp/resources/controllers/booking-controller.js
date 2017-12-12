var app = angular.module('booking-service', []);
app.controller('BookingController', function ($scope, $http) {

    $scope.existsBookingsByDate = "";
    $scope.bookingsData = [];

    $scope.getBookingsByDate = function () {
        let dateArr = $scope.existsBookingsByDate.split("/");
        console.log(dateArr);
        let url = "booking/findByDate?year=" + dateArr[0] + "&month=" + dateArr[1] + "&day=" + dateArr[2];
        $http.get(url)
            .success(function (data, status, headers, config) {
                $scope.bookingsData = data.bookingsDTOList;
                $scope.parseBookingData($scope.bookingsData);
            })
            .error(function (data, status, headers, config) {
                alert("error getting data");
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

});