package net.sf.l2j.gameserver.skills.effects;

import net.sf.l2j.gameserver.model.L2Effect;
import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.templates.skills.L2EffectType;

public class EffectDebuff extends L2Effect
{
	public EffectDebuff(Env env, EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.DEBUFF;
	}

	@Override
	public boolean onActionTime()
	{
		return false;
	}
}