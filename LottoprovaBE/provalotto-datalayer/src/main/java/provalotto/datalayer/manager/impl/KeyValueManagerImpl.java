package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.utility.ATSKeyValue;
import provalotto.datalayer.dao.KeyValueDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.manager.KeyValueManager;

@Component
public class KeyValueManagerImpl implements KeyValueManager {

	public static final Logger log = LoggerFactory.getLogger(KeyValueManagerImpl.class);

	@Autowired
	private KeyValueDAO keyValueDAO;

	@Override
	public List<List<KeyValueBean>> getAreaTopicSkillMap() {
		try {
			List<List<KeyValueBean>> ATSmap = new ArrayList<>();
			List<KeyValueBean> ATSrow;
			KeyValueBean keyValueBean;
			for (ATSKeyValue ATSinterfaceRow : keyValueDAO.getMap()) {
				ATSrow = new ArrayList<>();

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getAreaId());
				keyValueBean.setValue(ATSinterfaceRow.getAreaName());
				ATSrow.add(keyValueBean);

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getTopicId());
				keyValueBean.setValue(ATSinterfaceRow.getTopicName());
				ATSrow.add(keyValueBean);

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getSkillId());
				keyValueBean.setValue(ATSinterfaceRow.getSkillName());
				ATSrow.add(keyValueBean);

				ATSmap.add(ATSrow);
			}
			return ATSmap;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

}