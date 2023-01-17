angular.module('shop').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost/5555/core';
    const cartContextPath = 'http://localhost:5555/cart/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.productsPage = response.data.content;
        });
    };

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + '/core/api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }


    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/api/v1/cart/add/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }


    $scope.loadProducts();

});