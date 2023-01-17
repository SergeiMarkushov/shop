angular.module('shop').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost/5555/cart';
    const coreContextPath = 'http://localhost:5555/core';


    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart').then(function (responce) {
            $scope.cart = responce.data;
        });
    }


    $scope.deleteProductFromCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/delete/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/api/v1/cart/quantity_change',
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
        $http.get(contextPath + '/api/v1/cart/clear').then(function (responce) {
            $scope.loadCart();
        });
    }


    $scope.createOrder = function () {
        $http.post(coreContextPath + '/api/v1/orders/create_order')
            .then(function (response) {
                alert("Заказ оформлен")
                $scope.loadCart();
            })
    }


    $scope.loadCart();
});
