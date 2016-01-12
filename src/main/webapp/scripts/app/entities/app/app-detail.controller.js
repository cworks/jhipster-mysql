'use strict';

angular.module('hipstermysqlApp')
    .controller('AppDetailController', function ($scope, $rootScope, $stateParams, entity, App) {
        $scope.app = entity;
        $scope.load = function (id) {
            App.get({id: id}, function(result) {
                $scope.app = result;
            });
        };
        var unsubscribe = $rootScope.$on('hipstermysqlApp:appUpdate', function(event, result) {
            $scope.app = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
