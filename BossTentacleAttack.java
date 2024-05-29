public class BossTentacleAttack extends BossAttack{
    
    private float firerate;
    private int numTentacles;
    private float mainAngle;
    private float rotSpeed;
    private float projectileSpeed;
    private float angularDispBeforeSwitch;
    private boolean dirCCW;
    private static final String tentPic = "Asterisk1.png";

    public BossTentacleAttack(Boss boss){
        super(boss);
        firerate = 4;
        numTentacles = 5;
        mainAngle = 0;
        rotSpeed = .2f;
        projectileSpeed = 4;
        angularDispBeforeSwitch = (float)(Math.PI/2);
        dirCCW = true;
    }

    public void run(){
        try{
            while(activeThread() != null){
                float tentAngleInc = (float)(2*Math.PI/numTentacles);
                for(int i=0;i<numTentacles;i++){
                    float angle = mainAngle+i*tentAngleInc;
                    Vector2 velocity = new Vector2(projectileSpeed, angle, true);
                    BossProjectile.createProjectile(tentPic, boss().getPos(), .3f, velocity, true, true);
                }
                mainAngle += dirCCW ? rotSpeed : -rotSpeed;
                if(mainAngle >= angularDispBeforeSwitch || mainAngle < 0){
                    dirCCW = !dirCCW;
                }
                Sound.playSound("straw.wav");
                Thread.sleep((int)(1000/firerate));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
