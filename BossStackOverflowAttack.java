public class BossStackOverflowAttack extends BossAttack{
    
    private float speed;
    private float firerate;
    private int numLines;

    public BossStackOverflowAttack(Boss boss){
        super(boss);
        speed = 10;
        firerate = 4;
        numLines = 5;
    }

    public void run(){
        float[] angles = new float[numLines];
        for(int i=0;i<angles.length;i++){
            angles[i] = (float)(Math.random()*Math.PI);
        }

        try{
            while(activeThread() != null){
                for(float each : angles){
                    new BossProjectileBouncy("BlankSprite.png", boss().getPos(), .3f, new Vector2(speed, each, true),20);
                }
                Thread.sleep((int)(1000/firerate));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
