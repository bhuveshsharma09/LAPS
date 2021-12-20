package sg.edu.nus.LAPS.model.paging;

import org.springframework.data.domain.Page;

public class Paged<T> {
	private Page<T> page;

    private Paging paging;

	public Paged() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paged(Page<T> page, Paging paging) {
		super();
		this.page = page;
		this.paging = paging;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
