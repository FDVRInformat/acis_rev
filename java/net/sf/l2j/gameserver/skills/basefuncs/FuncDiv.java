package net.sf.l2j.gameserver.skills.basefuncs;

import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.skills.Stats;

public class FuncDiv extends Func
{
	public FuncDiv(Stats pStat, int pOrder, Object owner, Lambda lambda)
	{
		super(pStat, pOrder, owner, lambda);
	}

	@Override
	public void calc(Env env)
	{
		if (cond == null || cond.test(env))
			env.divValue(_lambda.calc(env));
	}
}