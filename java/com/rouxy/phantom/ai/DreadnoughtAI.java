package com.rouxy.phantom.ai;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rouxy.phantom.FakePlayer;
import com.rouxy.phantom.ai.addon.IConsumableSpender;
import com.rouxy.phantom.helpers.FakeHelpers;
import com.rouxy.phantom.model.HealingSpell;
import com.rouxy.phantom.model.OffensiveSpell;
import com.rouxy.phantom.model.SpellUsageCondition;
import com.rouxy.phantom.model.SupportSpell;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.ShotType;

public class DreadnoughtAI extends CombatAI implements IConsumableSpender {
	
	public DreadnoughtAI(FakePlayer character) {
		super(character);
	}


	@Override
	public void thinkAndAct() {		
		super.thinkAndAct();
		setBusyThinking(true);
		applyDefaultBuffs();
		handleShots();
		selfSupportBuffs();
		tryTargetRandomCreatureByTypeInRadius(FakeHelpers.getTestTargetClass(), FakeHelpers.getTestTargetRange());
		if(Config.FAKE_PLAYER_CAN_TARGET_REAL_PLAYER == true)
		{
			tryFlagTargetRandom(FakeHelpers.getFlagTargetClass(), FakeHelpers.getTestTargetRange());
		}
		
		tryAttackingUsingFighterOffensiveSkill();
		
		setBusyThinking(false);
	}

	@Override
	protected ShotType getShotType() {
		return ShotType.SOULSHOT;
	}	
	
	@Override
	protected double changeOfUsingSkill() {
		return 0.33;
	}

	@Override
	protected List<OffensiveSpell> getOffensiveSpells() {
		List<OffensiveSpell> _offensiveSpells = new ArrayList<>();
		_offensiveSpells.add(new OffensiveSpell(361, 1));
		_offensiveSpells.add(new OffensiveSpell(347, 2));		
		_offensiveSpells.add(new OffensiveSpell(48, 3));
		_offensiveSpells.add(new OffensiveSpell(452, 4));
		_offensiveSpells.add(new OffensiveSpell(36, 5));		
		return _offensiveSpells;
	}
	
	@Override
	protected List<SupportSpell> getSelfSupportSpells() {
		List<SupportSpell> _selfSupportSpells = new ArrayList<>();
		_selfSupportSpells.add(new SupportSpell(440, SpellUsageCondition.MISSINGCP, 1000, 1));
		return _selfSupportSpells;
	}
	
	@Override
	protected int[][] getBuffs()
	{
		return FakeHelpers.getFighterBuffs();
	}
	
	@Override
	protected List<HealingSpell> getHealingSpells()
	{		
		return Collections.emptyList();
	}

}
