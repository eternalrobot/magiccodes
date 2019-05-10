package Core.db.Service;

import Core.data.code;
import Core.db.Dao.codeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("codeService")
public class codeService {
    @Autowired
    Core.db.Dao.codeDao codeDao;

    @Transactional
    public void savecode(code code){
        codeDao.save(code);
    }
}
