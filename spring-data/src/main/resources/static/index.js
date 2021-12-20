angular.module('homework_app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/homework_app/api/v1';

    // console.log(123);


    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsPage = response.data;
        });
    };

    $scope.loadCart = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                console.log(response.data)
                $scope.CartsList = response.data;
            });
    };

    $scope.addProductToCart = function (productId) {
        console.log('Click! ' + productId);
        $http.get(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/carts/' + productId)
            .then(function (response) {
                alert('DELETED')
                $scope.loadCart();
            });
    };

    /* $scope.deleteProduct = function(productId){
         $http.delete(contextPath + '/products/' + productId)
             .then(function (response) {
                 alert('DELETED')
                 $scope.loadProducts();
             });
    }*/
    /*$scope.changeCost = function(productId, delta){
        // console.log('Click!');
         $http({
             url: contextPath + '/products/change_cost/',
             method: 'GET',
             params:{
                 productId: productId,
                 delta: delta
             }
         }).then(function (response) {
                $scope.loadProducts();
            });
    }*/

    /*$scope.createProduct = function(){
        console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function(response){
                $scope.loadProducts();
            });
    }*/

    /*$scope.filter = function(){
        $http({
            url: contextPath + '/products/cost_between',
            method: 'GET',
            params:{
                min: $scope.filter.min,
                max: $scope.filter.max
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsList = response.data
        });
    }*/

    $scope.loadProducts();
    $scope.loadCart();

});