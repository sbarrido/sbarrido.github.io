## Lock Mechanism Exercise (Propositional Logic)
  This program is a simplification of Car door Lock mechanisms. Extensive use of propositional logic
  is used to accomplish the various requirements to unlock a door. Simple Object-oriented programming
  represents the ability to apply these functions to any instance of a LockMech. 
```
/**
 * 
 * @author Sam Barrido/sbarrido
 * Last Edited: Feb. 4, 2018
 * Lab 4
 */
public class LockMech 
{
	// variables
	// used to construct a LockMech
	private int dashLeft;
	private int dashRight;
	private int inLeft;
	private int inRight;
	private int outLeft;
	private int outRight;
	private int child;
	private int master;
	private String gear;

	// Constructor for LockMech
	// variables needed to construct a LockMech are the following:
	// dashLeft, dashRight, inLeft, inRight, outLeft, outRight
	// child, master, gear
	public LockMech(int dashLeft, int dashRight, 
					int inLeft, int inRight, 
					int outLeft, int outRight,
					int child, int master, String gear)
	{
		this.dashLeft = dashLeft;
		this.dashRight = dashRight;
		this.inLeft = inLeft;
		this.inRight = inRight;
		this.outLeft = outLeft;
		this.outRight = outRight;
		this.child = child;
		this.master = master;
		this.gear = gear;
	}
	
	// method representative of minivan door controls
	public String doorControl()
	{
		// possible outcomes of any given configuration are listed
		String bothClose = "both doors stay closed";
		String rightClose = "right is closed";
		String leftClose = "left is closed";
		String leftOpen = "left door opens";
		String rightOpen = "right door opens";
		String result = "";
		
		// requirements to open door:
		// - master is activated (assigned 1)
		// - gear must be in park, "P"
		// else neither door will open
		if (!(this.master == 1) || this.gear != "P")
		{
			return bothClose;
		}
		
		// requirement to Open door is met
		else if ((this.master == 1) && (this.gear == "P"))
		{
			// =========controls for Left Door=============
			// checks if any action to open door is activated (assigned to 1)
			// only dash, inside handle, and outside handle can open door
			if((this.dashLeft == 1) || (this.inLeft == 1) || (this.outLeft == 1))
			{
				// checks if child safety is activated(assigned to 1) AND if inside handle is activated
				// if ONLY inside handle is activated and child safety is activated then remain close
				// else if outside handle or dashboard are activated than open left door
				if((this.inLeft == 1) && (this.child == 1) && !((this.outLeft == 1) || (this.dashLeft == 1)))
				{
					result += leftClose + " ";
				}
				else
				{
					result += leftOpen + " ";
				}
			}
			// =========controls for Right Door=============
			// checks if any action to open door is activated (assigned to 1)
			// only dash, inside handle, and outside handle can open door
			if((this.dashRight == 1) || (this.inRight == 1) || (this.outRight == 1))
			{
				// checks if child safety is activated(assigned to 1) AND if inside handle is activated
				// if ONLY inside handle is activated and child safety is activated then remain close
				// else if outside handle or dashboard are activated than open right door
				if((this.inRight == 1) && (this.child == 1) && !((this.outRight == 1) || (this.dashRight == 1)))
				{
					result += rightClose + " ";
				}
				else
				{
					result += rightOpen + " ";
				}
			}
		}
		// end conditions and return final result
		return result;
	}
	
	// testing client
	public static void main(String[] args)
	{
		// object variables layout: 
		// dashL, dashR, inL, inR, outL, outR, child, master, gear
		
		System.out.println("Dashboard Control Checks:");
		System.out.println("");
		// base requirements to open door is met, checks if leftDash activated works
		LockMech leftDashCheck = new LockMech(1, 0, 0, 0, 0, 0, 0, 1, "P");
		System.out.println(leftDashCheck.doorControl());
		// base requirements to open door is met, checks if rightDash activated works
		LockMech rightDashCheck = new LockMech(0, 1, 0, 0, 0, 0, 0, 1, "P");
		System.out.println(rightDashCheck.doorControl());
		System.out.println("");
		
		System.out.println("Inside Handle Control Checks:");
		System.out.println("");
		// base requirements to open door is met, checks if leftInCheck activated works
		LockMech leftInCheck = new LockMech(0, 0, 1, 0, 0, 0, 0, 1, "P");
		System.out.println(leftInCheck.doorControl());
		// base requirements to open door is met, checks if rightInCheck activated works
		LockMech rightInCheck = new LockMech(0, 0, 0, 1, 0, 0, 0, 1, "P");
		System.out.println(rightInCheck.doorControl());
		// base requirements to open is met, BUT child safety is on
		LockMech childLCheck = new LockMech(0, 0, 1, 0, 0, 0, 1, 1, "P");
		System.out.println(childLCheck.doorControl());
		// base requirements to open is met, BUT child safety is on
		LockMech childRCheck = new LockMech(0, 0, 0, 1, 0, 0, 1, 1, "P");
		System.out.println(childRCheck.doorControl());
		System.out.println("");
		
		System.out.println("Outside Handle Control Checks:");
		System.out.println("");
		// base requirements to open door is met, checks if leftInCheck activated works
		LockMech leftOutCheck = new LockMech(0, 0, 0, 0, 1, 0, 0, 1, "P");
		System.out.println(leftOutCheck.doorControl());
		// base requirements to open door is met, checks if rightInCheck activated works
		LockMech rightOutCheck = new LockMech(0, 0, 0, 0, 0, 1, 0, 1, "P");
		System.out.println(rightOutCheck.doorControl());
		System.out.println("");
		
		
		System.out.println("Master Control Check:");
		System.out.println("");
		// Each door switch is activated, BUT no master
		LockMech masterCheck = new LockMech(1, 1, 1, 1, 1, 1, 0, 0, "P");
		System.out.println(masterCheck.doorControl());
		System.out.println("");
		
		System.out.println("Gear Control Check:");
		System.out.println("");
		// each door switch is activated, BUT not in "P"
		LockMech gearCheck = new LockMech(1, 1, 1, 1, 1, 1, 0, 1, "N");
		System.out.println(gearCheck.doorControl());
		System.out.println("");
	}
}
```
