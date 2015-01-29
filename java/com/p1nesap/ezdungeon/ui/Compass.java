/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.p1nesap.ezdungeon.ui;

import com.p1nesap.noosa.Camera;
import com.p1nesap.noosa.Image;
import com.p1nesap.ezdungeon.Dungeon;
import com.p1nesap.ezdungeon.DungeonTilemap;
import com.p1nesap.utils.PointF;

public class Compass extends Image {

	private static final float RAD_2_G	= 180f / 3.1415926f;
	private static final float RADIUS	= 12;
	
	private int cell;
	private PointF cellCenter;
	
	private PointF lastScroll = new PointF();
	
	public Compass( int cell ) {
		
		super();
		copy( Icons.COMPASS.get() );
		origin.set( width / 2, RADIUS );
		
		this.cell = cell;
		cellCenter = DungeonTilemap.tileCenterToWorld( cell );
		visible = false;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (!visible) {
			visible = Dungeon.level.visited[cell] || Dungeon.level.mapped[cell]; 
		}
		
		if (visible) {			
			PointF scroll = Camera.main.scroll;
			if (!scroll.equals( lastScroll )) {
				lastScroll.set( scroll );
				PointF center = Camera.main.center().offset( scroll );
				angle = (float)Math.atan2( cellCenter.x - center.x, center.y - cellCenter.y ) * RAD_2_G;
			}
		}
	}
}