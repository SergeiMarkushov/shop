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
        $http.get('http://localhost:8190/shop-carts/api/v1/cart').then(function (responce) {
            $scope.cart = responce.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8190/shop-carts/api/v1/cart/add/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.get('http://localhost:8190/shop-carts/api/v1/cart/delete/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: 'http://localhost:8190/shop-carts/api/v1/cart/price_change',
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
        $http.get('http://localhost:8190/shop-carts/api/v1/cart/clear').then(function (responce) {
            $scope.loadCart();
        });
    }

    // $scope.createOrder = function (user) {
    //     $http({
    //         url: 'http://localhost:8190/shop-carts/api/v1/orders/create_order',
    //         method: 'POST',
    //         params: {
    //             user: user
    //         }
    //     }).then(function (response) {
    //         alert("Заказ оформлен!")
    //         $scope.clearCart();
    //         $scope.loadCart();
    //     });
    // }

    $scope.createOrder = function () {
        $http.post('http://localhost:8190/shop-carts/api/v1/orders/create_order')
            .then(function (response) {
                alert("Заказ оформлен")
            })
    }


    $scope.loadProducts();
    $scope.loadCart()
});