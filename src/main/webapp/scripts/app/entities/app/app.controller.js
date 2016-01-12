'use strict';

angular.module('hipstermysqlApp')
    .controller('AppController', function ($scope, $state, App) {

        $scope.apps = [];
        $scope.loadAll = function() {
            App.query(function(result) {
               $scope.apps = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.app = {
                name: null,
                createDate: null,
                id: null
            };
        };
    });
