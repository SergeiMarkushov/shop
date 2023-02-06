angular.module('shop').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart';
    const coreContextPath = 'http://localhost:5555/core';


    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/'  + $localStorage.winterMarketGuestCartId).then(function (responce) {
            $scope.cart = responce.data;
        });
    }


    $scope.deleteProductFromCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/delete/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/quantity_change',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    };


    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/clear').then(function (responce) {
            $scope.loadCart();
        });
    }


    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders/create_order')
            .then(function (response) {
                alert("Заказ оформлен")
                $scope.loadCart();
            })
    }


    $scope.loadCart();
});
