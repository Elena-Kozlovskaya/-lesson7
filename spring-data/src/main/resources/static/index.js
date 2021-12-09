angular.module('homework_app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/homework_app';

    // console.log(123);


    $scope.loadProducts = function() {
        $http.get(contextPath + '/products')
            .then(function (response) {
                // console.log(response.data)
                $scope.ProductsList = response.data;
            });
    };
    $scope.loadProducts();

     $scope.deleteProduct = function(productId){
         $http.get(contextPath + '/products/delete/' + productId)
             .then(function (response) {
                 alert('DELETED')
                 $scope.loadProducts();
             });
    }
    $scope.changeCost = function(productId, delta){
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
    }

    $scope.createProduct = function(){
        console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function(response){
                $scope.loadProducts();
            });
    }

    $scope.findProductByCostBetween = function(){
         console.log($scope.cost);
        $http({
            url: contextPath + '/products/cost_between',
            method: 'GET',
            params:{
                min: $scope.cost.min,
                max: $scope.cost.max
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsList = response.data
        });
    }


});