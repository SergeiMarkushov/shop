angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {

        $http.post('http://localhost:5555/auth/auth', $scope.user)
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


    // $scope.loadProducts = function () {
    //     $http.get('http://localhost:5555/core/api/v1/products').then(function (response) {
    //         $scope.productsList = response.data;
    //     });
    // };

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.ProductPage = response.data.content;
        });
    };

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (responce) {
            $scope.cart = responce.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/delete/' + productId).then(function (responce) {
            $scope.loadCart();
        });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/price_change',
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
        $http.get('http://localhost:5555/cart/api/v1/cart/clear').then(function (responce) {
            $scope.loadCart();
        });
    }


    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/create_order')
            .then(function (response) {
                alert("Заказ оформлен")
                $scope.loadCart();
            })
    }


    $scope.loadProducts();
    $scope.loadCart()
});