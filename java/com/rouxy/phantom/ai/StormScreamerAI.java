package com.rouxy.phantom.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rouxy.phantom.FakePlayer;
import com.rouxy.phantom.helpers.FakeHelpers;
import com.rouxy.phantom.model.HealingSpell;
import com.rouxy.phantom.model.OffensiveSpell;
import com.rouxy.phantom.model.SupportSpell;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.ShotType;

/**
 * @author Rouxy
 *
 */
public class StormScreamerAI extends CombatAI
{
	public StormScreamerAI(FakePlayer character)
	{
		super(character);		
	}
	
	
	@Override
	public void thinkAndAct()
	{
		super.thinkAndAct();
		setBusyThinking(true);
		applyDefaultBuffs();
		handleShots();
		tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetClass(), FakeHelpers.getTestTargetRange());
		if(Config.FAKE_PLAYER_CAN_TARGET_REAL_PLAYER == true)
		{
			tryFlagTargetRandom(FakeHelpers.getFlagTargetClass(), FakeHelpers.getTestTargetRange());
		}
		tryAttackingUsingMageOffensiveSkill();
		
		setBusyThinking(false);
	}
	
	@Override
	protected ShotType getShotType()
	{
		return ShotType.BLESSED_SPIRITSHOT;
	}
	
	@Override
	protected List<OffensiveSpell> getOffensiveSpells()
	{
		List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
		_offensiveSpells.add(new OffensiveSpell(1341, 1));
		_offensiveSpells.add(new OffensiveSpell(1343, 2));
		_offensiveSpells.add(new OffensiveSpell(1234, 3));
		_offensiveSpells.add(new OffensiveSpell(1239, 4));
		return _offensiveSpells; 
	}
	
	@Override
	protected int[][] getBuffs()
	{
		return FakeHelpers.getMageBuffs();
	}

	@Override
	protected List<HealingSpell> getHealingSpells()
	{		
		return Collections.emptyList();
	}
	
	@Override
	protected List<SupportSpell> getSelfSupportSpells() {
		return Collections.emptyList();
	}
}
