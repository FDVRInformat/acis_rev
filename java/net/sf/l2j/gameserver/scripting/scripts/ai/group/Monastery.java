/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.scripting.scripts.ai.group;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.geoengine.GeoEngine;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Attackable;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.base.Sex;
import net.sf.l2j.gameserver.scripting.EventType;
import net.sf.l2j.gameserver.scripting.scripts.ai.L2AttackableAIScript;
import net.sf.l2j.gameserver.templates.skills.L2SkillType;

/**
 * This script holds MoS monsters behavior. If they see you with an equipped weapon, they will speak and attack you.
 */
public class Monastery extends L2AttackableAIScript
{
	private static final int[] BROTHERS_SEEKERS_MONKS =
	{
		22124,
		22125,
		22126,
		22127,
		22129
	};
	
	private static final int[] GUARDIANS_BEHOLDERS =
	{
		22134,
		22135
	};
	
	public Monastery()
	{
		super("ai/group");
	}
	
	@Override
	protected void registerNpcs()
	{
		addEventIds(BROTHERS_SEEKERS_MONKS, EventType.ON_AGGRO, EventType.ON_SPAWN, EventType.ON_SPELL_FINISHED);
		addEventIds(GUARDIANS_BEHOLDERS, EventType.ON_SKILL_SEE);
	}
	
	@Override
	public String onAggro(Npc npc, Player player, boolean isPet)
	{
		if (!npc.isInCombat())
		{
			if (player.getActiveWeaponInstance() != null)
			{
				npc.setTarget(player);
				npc.broadcastNpcSay(((player.getAppearance().getSex() == Sex.FEMALE) ? "Sister " : "Brother ") + player.getName() + ", move your weapon away!");
				
				switch (npc.getNpcId())
				{
					case 22124:
					case 22126:
						npc.doCast(SkillTable.getInstance().getInfo(4589, 8));
						break;
					
					default:
						attack(((Attackable) npc), player);
						break;
				}
			}
			else if (((Attackable) npc).getMostHated() == null)
				return null;
		}
		return super.onAggro(npc, player, isPet);
	}
	
	@Override
	public String onSkillSee(Npc npc, Player caster, L2Skill skill, WorldObject[] targets, boolean isPet)
	{
		if (skill.getSkillType() == L2SkillType.AGGDAMAGE && targets.length != 0)
		{
			for (WorldObject obj : targets)
			{
				if (obj.equals(npc))
				{
					npc.broadcastNpcSay(((caster.getAppearance().getSex() == Sex.FEMALE) ? "Sister " : "Brother ") + caster.getName() + ", move your weapon away!");
					attack(((Attackable) npc), caster);
					break;
				}
			}
		}
		return super.onSkillSee(npc, caster, skill, targets, isPet);
	}
	
	@Override
	public String onSpawn(Npc npc)
	{
		for (Player target : npc.getKnownTypeInRadius(Player.class, npc.getTemplate().getAggroRange()))
		{
			if (!target.isDead() && GeoEngine.getInstance().canSeeTarget(npc, target))
			{
				if (target.getActiveWeaponInstance() != null && !npc.isInCombat() && npc.getTarget() == null)
				{
					npc.setTarget(target);
					npc.broadcastNpcSay(((target.getAppearance().getSex() == Sex.FEMALE) ? "Sister " : "Brother ") + target.getName() + ", move your weapon away!");
					
					switch (npc.getNpcId())
					{
						case 22124:
						case 22126:
						case 22127:
							npc.doCast(SkillTable.getInstance().getInfo(4589, 8));
							break;
						
						default:
							attack(((Attackable) npc), target);
							break;
					}
				}
			}
		}
		return super.onSpawn(npc);
	}
	
	@Override
	public String onSpellFinished(Npc npc, Player player, L2Skill skill)
	{
		if (skill.getId() == 4589)
			attack(((Attackable) npc), player);
		
		return super.onSpellFinished(npc, player, skill);
	}
}