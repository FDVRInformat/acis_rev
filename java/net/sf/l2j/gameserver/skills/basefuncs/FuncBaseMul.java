package net.sf.l2j.gameserver.skills.basefuncs;

import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.skills.Stats;

public class FuncBaseMul extends Func
{
	public FuncBaseMul(Stats pStat, int pOrder, Object owner, Lambda lambda)
	{
		super(pStat, pOrder, owner, lambda);
	}

	@Override
	public void calc(Env env)
	{
		if (cond == null || cond.test(env))
			env.addValue(env.getBaseValue() * _lambda.calc(env));
	}
}