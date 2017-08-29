
// controller for the modal window
wzrdfrmApp.controller('CharClassChangeController', function (APIService, FarmService, $scope, $uibModalInstance, $log) {

    $scope.charClasses;
    $scope.currCharClass;

    ($scope.fetchCharClasses = function() {
        APIService.getAllCharClasses(function(response) {
            $scope.charClasses = response.data.allCharClasses;
            $scope.currCharClass = response.data.currCharClass;
        });
    })();

    $scope.switchTo = function(charClass) {
        var switchRequest = FarmService.getCharClassRequest(charClass.id);

        APIService.switchCharClass(switchRequest, function(response) {
            $scope.fetchCharClasses();
        });
    }

    $scope.unlock = function(charClass) {
        var unlockCharClassRequest = FarmService.getCharClassRequest(charClass.id);

        APIService.unlockCharClass(unlockCharClassRequest, function(response) {
            $scope.fetchCharClasses();
        });
    }

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});