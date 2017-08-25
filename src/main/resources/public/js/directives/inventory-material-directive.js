
avalonApp.directive('inventoryMaterial', function() {
    return {
        restrict: 'E',
        scope: {
            inventoryMaterial: '=',
        },
        template: '<img src="{{getMaterialIcon(inventoryMaterial)}}" class="icon-md" /> \
                   <span>{{inventoryMaterial.quantity}}</span> \
                   <span>{{inventoryMaterial.material.name}}</span> \
                   <span>{{inventoryMaterial.material.capacityRequirement}} Cap</span>',
        controller: function (ImageService, $scope) {
            $scope.getMaterialIcon = function(invMaterial) {
                // just return the material type icon for now
                return ImageService.getMaterialTypeIcon($scope.inventoryMaterial.material.materialType);
            }
        }
    }
});


