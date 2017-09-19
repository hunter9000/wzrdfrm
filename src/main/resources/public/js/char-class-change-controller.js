
// controller for the modal window
wzrdfrmApp.controller('CharClassChangeController', function (APIService, FarmService, $scope, $uibModalInstance, $log) {

    $scope.charClasses;
    $scope.currCharClass;
    $scope.numClassUnlockOrbs;

    ($scope.fetchCharClasses = function() {
        APIService.getAllCharClasses(function(response) {
            $scope.charClasses = response.data.allCharClasses;
            $scope.currCharClass = response.data.currCharClass;
            $scope.numClassUnlockOrbs = response.data.numClassUnlockOrbs;
        });
    })();

    // charClass.unlocked && currCharClass.id == charClass.id
    $scope.isCurrentClass = function(charClass) {
        return charClass.unlocked && $scope.currCharClass.id == charClass.id;
    }

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