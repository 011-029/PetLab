package mgr;

import facade.DataEngineImpl;

public abstract class PetRecordMgr<T extends PetOwned & Manageable>
        extends DataEngineImpl<T> {

    protected int nextIndexId = -1;

    public void printByOwner(String ownerId) {
        for (T m : mList) {
            // 유저 ID 당 등록된 펫이 한 마리라고 가정
            if (m.getOwnerId().equals(ownerId))
                m.print();
        }
    }

    public T findByIndexId(int indexId) {
        // 인덱스 번호로 객체 찾기
        for (T m : mList) {
            if (m.getIndexId() == indexId)
                return m;
        }
        return null;
    }

    public boolean removeByIndexId(int indexId) {
        return mList.removeIf(m -> m.getIndexId() == indexId);
        // 삭제된 객체가 있으면 true, 없으면 false 반환
    }

    protected void initNextIndexId() {
        int max = 0;
        for (T r : mList) {
            if (r.getIndexId() > max)
                max = r.getIndexId();
        }
        nextIndexId = max + 1;
    }

    protected void addWithIndexId(T record) {
        record.setIndexId(nextIndexId++);
        mList.add(record);
    }
}
