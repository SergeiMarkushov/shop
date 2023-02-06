angular.module('shop').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null
            }
        }).then(function (response) {
            // console.log(response.data);
            // $scope.productsPage = response.data.content;

            $scope.productsPage = response.data;
            $scope.indexNumber = $scope.productsPage.totalPages;
            $scope.generatePagesList($scope.productsPage.totalPages);
        });
    };

    $scope.generatePagesList = function (totalPages) {
        $scope.pagesList = [];
        for (let i = 0; i < totalPages; i++) {
            $scope.pagesList.push(i + 1);
        }
    }

    $scope.isThereIndex = function () {
        if ($scope.indexNumber > 1) {
            return true;
        } else {
            return false;
        }
    }

    $scope.previousPage = function (page, delta) {
        if ((page + delta) >= 0) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.nextPage = function (page, delta) {
        if ((page + delta) <= $scope.indexNumber) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:5555/core/api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }


    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/add/' + productId).then(function (responce) {
        });
    }


    $scope.loadProducts();

});