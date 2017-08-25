
avalonApp.controller('craftingController', function(APIService, InventoryService, ImageService, ItemService, $scope, $window, $routeParams, $log) {
    $scope.recipes;
    $scope.inventoryMaterials;

    $scope.selectedRecipe = null;
    $scope.remainingCapacity = null;
//    $scope.selectedRecipeCraftable = false;

    $scope.mats;
    $scope.selectedMat = null;

    $scope.baseMats = [];
    $scope.extraMats = [];


    $scope.materialFilterOptions = ItemService.getMaterialTypes().map(function(currentValue, index, array) {
        return {'label': currentValue, 'image': ImageService.getMaterialTypeIcon(currentValue), 'filter': currentValue}
    });

    $scope.materialFilter = [];

    $scope.filterMaterialsFunction = function(hash) {
        return ($scope.materialFilter.indexOf(hash.material.materialType) !== -1);
    };


    APIService.getChar($routeParams.charId, function(response) {
        $scope.recipes = response.data.recipes;
        $scope.inventoryMaterials = response.data.inventoryMaterials;
        $scope.resetMats();
    });

    $scope.resetMats = function() {
        // copy the mats objects
        $scope.mats = JSON.parse(JSON.stringify($scope.inventoryMaterials));
        $scope.extraMats = [];
        $scope.baseMats = [];
    }

    $scope.recipeSelect = function(recipe, event) {
        // get from recipe info, set base item, base mats, inventory
        $scope.selectedRecipe = recipe;
        $scope.remainingCapacity = recipe.extraCapacity;

        $log.debug('controller says this recipe selected ', recipe);
        $scope.resetMats();      // reset the working list of mats

        $log.debug('recipe craftable ', $scope.selectedRecipe.craftable);


        if ($scope.selectedRecipe.craftable) {
            // remove all the reqs from the working list of mats
            for (var i = 0; i < recipe.recipeReqs.length; i++) {
                var mat = recipe.recipeReqs[i];
                InventoryService.removeFromList($scope.mats, mat.material.name, mat.quantity);
            }
        }
    }

    $scope.matSelect = function(element, event) {
        console.log('clicked material!');
        $log.debug(element);
        $scope.selectedMat = element;
    }

    $scope.addMatToRecipe = function(invMaterial, event) {
        $log.debug('add to recipe');

        // check the capacity of the recipe first
        var capacityReq = invMaterial.material.capacityRequirement;
        if ($scope.remainingCapacity < capacityReq) {
            return;     // can't add material to recipe's extra mats
        }
        $scope.remainingCapacity -= capacityReq;

        // split 1 off the item
        var newItem = InventoryService.splitItem(invMaterial, 1);

        // add to extramats
        // find in the list
        InventoryService.addToList($scope.extraMats, newItem);

        // if quantity is now 0, remove from mats list

    }

    $scope.submitRecipe = function() {
        var extraMats = $scope.extraMats.map(function (mat) {
            return {
                'materialId': mat.material.id,
                'quantity': mat.quantity
            };
        });

        var payload = {
            'recipeId': $scope.selectedRecipe.id,
            'extraMats': extraMats
        };

        APIService.craftRecipe($routeParams.charId, payload, function(response) {
            $log.debug(response.data);
        });
    }

    $scope.getRecipeIcon = function(recipe) {
        return ImageService.getEquipmentSlotIcon(recipe.item.bodySlot);
    }

});