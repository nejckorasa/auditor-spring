package com.marand.auditor;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Nejc Korasa
 */

public class AuditInfo
{
  private String sender;
  @NotBlank private String userId;
  @NotBlank private String user;
  private String returnResult;
  private String method;
  private String parameters;

  public String getSender()
  {
    return sender;
  }

  public void setSender(final String sender)
  {
    this.sender = sender;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(final String userId)
  {
    this.userId = userId;
  }

  public String getUser()
  {
    return user;
  }

  public void setUser(final String user)
  {
    this.user = user;
  }

  public String getReturnResult()
  {
    return returnResult;
  }

  public void setReturnResult(final String returnResult)
  {
    this.returnResult = returnResult;
  }

  public String getMethod()
  {
    return method;
  }

  public void setMethod(final String method)
  {
    this.method = method;
  }

  public String getParameters()
  {
    return parameters;
  }

  public void setParameters(final String parameters)
  {
    this.parameters = parameters;
  }

  @Override
  public String toString()
  {
    return String.format(
        "AuditInfo{sender='%s', userId='%s', user='%s', returnResult='%s', method='%s', parameters='%s'}",
        sender,
        userId,
        user,
        returnResult,
        method,
        parameters);
  }
}
