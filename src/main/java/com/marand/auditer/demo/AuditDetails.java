package com.marand.auditer.demo;

/**
 * @author Nejc Korasa
 */

public class AuditDetails
{
  private String sender;
  private String details;

  public String getSender()
  {
    return sender;
  }

  public void setSender(final String sender)
  {
    this.sender = sender;
  }

  public String getDetails()
  {
    return details;
  }

  public void setDetails(final String details)
  {
    this.details = details;
  }
}
