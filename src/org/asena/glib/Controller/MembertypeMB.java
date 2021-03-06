
package org.asena.glib.Controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.asena.baseService.JPAOp;
import org.asena.common.JSFHelper;
import org.asena.common.baseManagedBeanController;
import org.asena.common.exception.gException;
import org.asena.glib.Entity.Membertype;
import org.asena.glib.ServiceInterface.MembertypeUCService;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "MembertypeMB")
@ViewScoped

public class MembertypeMB extends baseManagedBeanController<Membertype> implements Serializable
{
	private static final long serialVersionUID = 7175212628505956385L;

	public MembertypeMB()
	{
	}


	//Services Deceleration
	@Autowired
	private MembertypeUCService membertypeService;




	//base Object, relations Lists (1-* objects)




	@Override
	protected void ResetBaseObject()
	{
		super.ResetBaseObject();

		// new Base Object 
		baseEntity = new Membertype();

		// new other Objects and set them into Base object



		// refresh Lists
		baseEntityList = membertypeService.FindAll("id", JPAOp.Asc);
	}




	//UC: Add/Edit
	public void AddEdit() throws gException
	{
		String result = "";
		try
		{
			if (isedit)
				membertypeService.Edit(baseEntity);
			else
				result = membertypeService.Add(baseEntity);

			ResetBaseObject();


			if (result.length() >= 1)
				JSFHelper.addInfoMessage(result);
			else
				JSFHelper.addInfoMessage("عملیات ثبت / ویرایش اطلاعات با موفقیت انجام شد");

		}
		catch (Exception ex)
		{
			CallCatch(ex);
		}

	}




	//UC: Remove
	public void Remove(Membertype baseEntity)
	{
		try
		{
			membertypeService.Remove(baseEntity);
			ResetBaseObject();
			JSFHelper.addInfoMessage("عملیات حذف اطلاعات با موفقیت انجام شد.");
		}
		catch (Exception ex)
		{
			CallCatch(ex);
		}
	}




	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    Setters & Getters
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    Setters & Getters




}
