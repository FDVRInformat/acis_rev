package net.sf.l2j.gameserver.skills.basefuncs;

import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.skills.Stats;

public class FuncSub extends Func
{
	public FuncSub(Stats pStat, int pOrder, Object owner, Lambda lambda)
	{
		super(pStat, pOrder, owner, lambda);
	}

	@Override
	public void calc(Env env)
	{
		if (cond == null || cond.test(env))
			env.subValue(_lambda.calc(env));
	}
}