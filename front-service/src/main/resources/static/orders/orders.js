angular.module('shop').controller('ordersController', function ($scope, $http) {
    const coreContextPath = 'http://localhost:5555/core';


    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders').then(function (responce) {
            $scope.orders = responce.data;
        });
    }

    $scope.loadOrders();
});
