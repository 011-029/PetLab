package mgr;

import facade.DataEngineImpl;

public abstract class PetRecordMgr<T extends PetOwned & Manageable>
extends DataEngineImpl<T> {

    public void printByPet(String ownerId) {
        for (T m: mList) {
            // 유저 ID 당 등록된 펫이 한 마리라고 가정
            if (m.getOwnerId().equals(ownerId))
                m.print();
        }
    }
}
