'use strict';

angular.module('hipstermysqlApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app', {
                parent: 'entity',
                url: '/apps',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hipstermysqlApp.app.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/app/apps.html',
                        controller: 'AppController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('app');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('app.detail', {
                parent: 'entity',
                url: '/app/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'hipstermysqlApp.app.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/app/app-detail.html',
                        controller: 'AppDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('app');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'App', function($stateParams, App) {
                        return App.get({id : $stateParams.id});
                    }]
                }
            })
            .state('app.new', {
                parent: 'app',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/app/app-dialog.html',
                        controller: 'AppDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    createDate: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('app', null, { reload: true });
                    }, function() {
                        $state.go('app');
                    })
                }]
            })
            .state('app.edit', {
                parent: 'app',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/app/app-dialog.html',
                        controller: 'AppDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['App', function(App) {
                                return App.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('app', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('app.delete', {
                parent: 'app',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/app/app-delete-dialog.html',
                        controller: 'AppDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['App', function(App) {
                                return App.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('app', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
