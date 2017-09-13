
wzrdfrmApp.directive('classLevel', function() {
	return {
		template:
			`<div class="class-level-wrapper">
			    <!-- name -->
                <span class="class-name">{{charClass.charClassDefinition.name}}</span>
                <!-- level -->
                <span class="class-level">{{charClass.currentLevel}}</span>
                <!-- xp progress bar -->
                <span class="class-xp-bar">
                    <uib-progressbar animate="false" max="charClass.xpNeededToLevel" value="charClass.currentXP">
                        <span style="color:white; white-space:nowrap;">
                            {{charClass.currentXP}} / {{charClass.xpNeededToLevel}}
                        </span>
                    </uib-progressbar>
                </span>
			</div>`,
		restrict: 'E',
		scope: {
			charClass: '='
		},
	}
});
