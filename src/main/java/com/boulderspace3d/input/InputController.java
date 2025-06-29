package com.boulderspace3d.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Deux joysticks (3D & 2D) + toggle tir.
 */
public class InputController extends InputAdapter {
    public static class Joystick3D {
        private final Vector3 center;
        private final float radius;
        private Vector3 knob;
        public boolean touched;
        public Joystick3D(float x,float y,float z,float r){
            center=new Vector3(x,y,z);
            radius=r;
            knob=new Vector3(x,y,z);
            touched=false;
        }
        public Vector3 getDirection() {
            if(!touched) return new Vector3(0,0,0);
            Vector3 dir = new Vector3(knob).sub(center);
            if(dir.len()>radius) dir.nor().scl(radius);
            return dir.scl(1f/radius);
        }
        public boolean isTouched(int x,int y) { return center.dst(x,y,0)<=radius; }
        public void down(int x,int y){ touched=true; update(x,y); }
        public void drag(int x,int y){ update(x,y); }
        public void up(){ touched=false; knob.set(center); }
        private void update(int x,int y){
            Vector3 tmp=new Vector3(x,y,0).sub(center);
            if(tmp.len()>radius) tmp.nor().scl(radius);
            knob.set(center).add(tmp);
        }
    }
    public static class Joystick2D {
        private final Vector3 center; private final float radius; private Vector3 knob; public boolean touched;
        public Joystick2D(float x,float y,float r){ center=new Vector3(x,y,0); radius=r; knob=new Vector3(x,y,0); touched=false; }
        public Vector3 getDirection(){ if(!touched) return new Vector3(0,0,0); Vector3 dir=new Vector3(knob).sub(center); if(dir.len()>radius) dir.nor().scl(radius); return dir.scl(1f/radius);}
        public boolean isTouched(int x,int y){return center.dst(x,y,0)<=radius;}
        public void down(int x,int y){ touched=true; update(x,y);} public void drag(int x,int y){update(x,y);} public void up(){touched=false; knob.set(center);}
        private void update(int x,int y){ Vector3 tmp=new Vector3(x,y,0).sub(center); if(tmp.len()>radius) tmp.nor().scl(radius); knob.set(center).add(tmp);}
    }

    private final Joystick3D orientJoy;
    private final Joystick2D moveJoy;
    private boolean fireLeft=false, fireRight=false;

    public InputController(Viewport vp) {
        float w=vp.getScreenWidth(), h=vp.getScreenHeight(), r=Math.min(w,h)*0.15f;
        orientJoy=new Joystick3D(w*0.2f,h*0.2f,0,r);
        moveJoy  =new Joystick2D(w*0.8f,h*0.2f,r);
        Gdx.input.setInputProcessor(this);
    }

    public float getYawInput()    { return orientJoy.getDirection().x; }
    public float getPitchInput()  { return orientJoy.getDirection().y; }
    public float getRollInput()   { return orientJoy.getDirection().z; }
    public float getForwardInput(){ return moveJoy.getDirection().y; }
    public float getStrafeInput() { return moveJoy.getDirection().x; }
    public boolean isLeftFiring() { return fireLeft; }
    public boolean isRightFiring(){ return fireRight; }

    @Override public boolean touchDown(int x,int y,int p,int b){
        y=Gdx.graphics.getHeight()-y;
        if(orientJoy.isTouched(x,y)){orientJoy.down(x,y);return true;}
        if(moveJoy.isTouched(x,y)){moveJoy.down(x,y);return true;}
        if(x<Gdx.graphics.getWidth()/2) fireLeft = !fireLeft; else fireRight = !fireRight;
        return true;
    }
    @Override public boolean touchDragged(int x,int y,int p){
        y=Gdx.graphics.getHeight()-y;
        if(orientJoy.touched){orientJoy.drag(x,y);return true;}
        if(moveJoy.touched)  {moveJoy.drag(x,y);  return true;}
        return false;
    }
    @Override public boolean touchUp(int x,int y,int p,int b){
        if(orientJoy.touched){orientJoy.up();return true;}
        if(moveJoy.touched)  {moveJoy.up();  return true;}
        return false;
    }
}