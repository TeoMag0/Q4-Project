public class BossTransformer implements Runnable {
    

    public static void transformBoss(){
        new Thread(new BossTransformer()).start();
    }

    public void run(){
        for(int i=0;i<8;i++){
            float angle = (float)Math.PI/4*i;
            Vector2 velocity = new Vector2(1, angle, true);
            new BossProjectile("Asterisk1.png", Screen.boss.getPos(), 1, velocity, .5f, true, false, true, Shape.CIRCLE);
        }
        try{
            Thread.sleep(250);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Screen.boss.phase2();
    }
}
