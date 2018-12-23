package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Chunk extends shit {
    Sprite sprite;
    Chunk(TextureRegion textureRegion){
        region = textureRegion;
        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());
        sprite = new Sprite(region);
    }


}
