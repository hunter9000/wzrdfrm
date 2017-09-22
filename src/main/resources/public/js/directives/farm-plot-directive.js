
wzrdfrmApp.directive('farmPlot', function(FarmService) {
	return {
		template:
            `<div ng-show="plot.unlocked">

                 <!-- Plant name and timer -->
                 <div ng-show="plot.plant">
                     <span>{{plot.plant.name}}</span>
                     <!--<span>Date: {{plot.plantDate | date:'MM-dd-yyyy HH:mm:ss'}}</span>-->

                     <!-- progress bar -->
                     <!-- timer's progressBar variable starts at 100 and counts down to 0 for some reason -->
                     <timer start-time="plot.plantDate" end-time="plot.endDate" interval="1000">
                         <!--[{{progressBar}}]-->
                         <div class="progress active {{displayProgressActive}}" ng-show="progressBar > 0">
                             <div class="progress-bar bar" style="width: calc(100% - {{progressBar}}%);">
                                {{days}} days, {{hours}} hours, {{minutes}} minutes, {{seconds}} seconds
                             </div>
                         </div>

                         <button class="btn" ng-show="progressBar <= 0 || !progressBar" ng-click="harvest(plot)">Harvest</button>
                     </timer>
                 </div>

                 <!-- Plant selection if nothing is planted -->
                 <div class="dropdown" ng-hide="plot.plant">
                     <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                         Plant <span class="caret"></span>
                     </button>
                     <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                         <li ng-repeat="seed in seeds">
                             <a href="#" ng-click="plant(plot, seed.plant)">
                                 {{seed.plant.name}}
                             </a>
                         </li>
                     </ul>
                 </div>

             </div>`,
		restrict: 'E',
		scope: {
			plot: '=',
			seeds: '=',
			consumables: '=',
		},
		controller: function($scope, FarmService) {
            $scope.plant = function(plot, plant) {
                FarmService.plantCrop(plot, plant);

//                var plantCropRequest = FarmService.getPlantCropRequest(plant);
//
//                APIService.plantCrop(plot.id, plantCropRequest, function(response) {
//                    var farmPlot = response.data;
//                    $scope.farm.farmPlots[farmPlot.row][farmPlot.col] = farmPlot;
//                });
            }

            $scope.harvest = function(plot) {
                FarmService.harvestCrop(plot);

//                APIService.harvestCrop(plot.id, function(response) {
//                    $scope.harvestedMaterials = response.data;
//                    NotificationService.createHarvestRewardNotification($scope.harvestedMaterials);
//                    $scope.getFarm();
//                });
            }
		}
	}
});
