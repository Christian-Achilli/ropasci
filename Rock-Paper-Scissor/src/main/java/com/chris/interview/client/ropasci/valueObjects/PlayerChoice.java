package com.chris.interview.client.ropasci.valueObjects;

public abstract class PlayerChoice {
    public abstract String getDescription();

    public abstract ChoiceCode getCode();

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((getCode() == null) ? 0 : getCode().hashCode());
	result = prime
		* result
		+ ((getDescription() == null) ? 0 : getDescription().hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PlayerChoice other = (PlayerChoice) obj;
	if (getCode() == null) {
	    if (other.getCode() != null)
		return false;
	} else if (!getCode().equals(other.getCode()))
	    return false;
	if (getDescription() == null) {
	    if (other.getDescription() != null)
		return false;
	} else if (!getDescription().equals(other.getDescription()))
	    return false;
	return true;
    }

}
