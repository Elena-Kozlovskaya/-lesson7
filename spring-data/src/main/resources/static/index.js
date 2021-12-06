angular.module('homework_app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/homework_app';

    $scope.loadProducts = function() {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };
     $scope.deleteProduct = function(productId){
         $http.get(contextPath + '/products/delete/' + productId)
             .then(function (response) {
                 alert('DELETED')
                 $scope.loadProducts();
             });
    }
    $scope.changeCost = function(productId, delta){
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

    $scope.loadProducts();
});