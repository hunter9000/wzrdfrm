package avalon.model;

public class Enemy extends CellEntity {

    private long cellId;        // the cell this is in
    // enemy type
    // current stats?

    EnemyType type;
    int x, y;							// location on the grid
    //Stats stats;						// stats
    boolean isAwareOfPlayer;			// is aware of player?

    public enum EnemyType {
        Rat(1, 2),
        Skeleton(2, 3);

        public final int levelMin, levelMax;
        private EnemyType(int levelMin, int levelMax) {
            this.levelMin = levelMin;
            this.levelMax = levelMax;
        }
    }

}
