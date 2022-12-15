angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/shop/api/v1';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath +'/products/' + productId).then( function (response) {
            alert(response.data.title);
        });
    }

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart').then(function (responce) {
           $scope.cart = responce.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }


    $scope.loadProducts();
    $scope.loadCart()
});