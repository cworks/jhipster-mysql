'use strict';

angular.module('hipstermysqlApp').controller('AppDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'App',
        function($scope, $stateParams, $uibModalInstance, entity, App) {

        $scope.app = entity;
        $scope.load = function(id) {
            App.get({id : id}, function(result) {
                $scope.app = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('hipstermysqlApp:appUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.app.id != null) {
                App.update($scope.app, onSaveSuccess, onSaveError);
            } else {
                App.save($scope.app, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreateDate = {};

        $scope.datePickerForCreateDate.status = {
            opened: false
        };

        $scope.datePickerForCreateDateOpen = function($event) {
            $scope.datePickerForCreateDate.status.opened = true;
        };
}]);
