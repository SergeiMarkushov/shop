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
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    $scope.deleteFromCart = function (productId) {
        $http.delete(contextPath +'/cart/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'POST',
            params: {
                productId: productId
            }
        }).then(function (response) {
            $scope.loadCart();
        }) ;
    };


    $scope.loadProducts();
    $scope.loadCart();
});