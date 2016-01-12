'use strict';

angular.module('hipstermysqlApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


