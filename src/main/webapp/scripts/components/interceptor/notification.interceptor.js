 'use strict';

angular.module('hipstermysqlApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-hipstermysqlApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-hipstermysqlApp-params')});
                }
                return response;
            }
        };
    });
