package ru.stqa.pft.rest;


import java.util.Objects;

public class Issue {
  private int Id;
  private String subject;
  private String description;
  private String state_name;

  public int getId() {
    return Id;
  }

  public Issue withId(int id) {
    Id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public String getState_name() {
    return state_name;
  }

  public Issue withState_name(String issueStatus) {
    this.state_name = issueStatus;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return Id == issue.Id && Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description) && Objects.equals(state_name, issue.state_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Id, subject, description, state_name);
  }
}