angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/shop/api/v1';

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/shop/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(responce) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };
    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkAuth = function () {
        $http.get('http://localhost:8189/shop/auth_check').then(function (response) {
            alert(response.data.value);
        });
    };

    if ($localStorage.winterMarketUser) {
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 100);
            if (currentTime > payload.exp) {
                console.log("Token is expired!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Autorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    }


    $scope.loadProducts = function () {
        $http.get(contextPath + '/products').then(function (response) {
            $scope.productsList = response.data;
        });
    };

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

    $scope.deleteProductFromCart = function (productId) {
        $http.get(contextPath + '/cart/delete/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/cart/price_change',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear').then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function (productId) {
        $http({
            url: contextPath + '/orders/create_order',
            method: 'POST',
            params: {
                productId: productId
            }
        }).then(function (response) {
            $scope.clearCart();
            $scope.loadCart();
        });
    }


    $scope.loadProducts();
    $scope.loadCart()
});