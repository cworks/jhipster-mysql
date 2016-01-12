'use strict';

angular.module('hipstermysqlApp')
	.controller('AppDeleteController', function($scope, $uibModalInstance, entity, App) {

        $scope.app = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            App.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
