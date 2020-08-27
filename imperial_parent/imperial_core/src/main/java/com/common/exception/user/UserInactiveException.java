package com.common.exception.user;

/**
 * 用户未激活异常类
 *
 * @author lz
 */
public class UserInactiveException  extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserInactiveException()
    {
        super("user.inactive", null);
    }
}