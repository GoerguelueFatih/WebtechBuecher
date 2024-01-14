import { describe, it, expect, beforeEach, vi } from 'vitest';
import { mount } from '@vue/test-utils';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import CategorySearch from '../CategorySearch.vue';

vi.mock('@okta/okta-vue', () => ({
    useAuth: () => ({
        getAccessToken: vi.fn().mockResolvedValue('mock-access-token'),
    }),
}));

const mock = new MockAdapter(axios);
const mockCategories = [{ id: 1, name: 'Fantasy' }, { id: 2, name: 'Sci-Fi' }];
const mockBooks = [{ id: 1, title: 'Book', author: 'Author', price: 10 }];

beforeEach(() => {
    mock.reset();
    mock.onGet('http://localhost:8080/category').reply(200, mockCategories);
    mock.onGet(new RegExp('http://localhost:8080/books\\?category=.*')).reply(200, mockBooks);
});

describe('CategorySearch Component', () => {
    it('fetches and renders categories on mount', async () => {
        const wrapper = mount(CategorySearch);

        // Wait for component to process the update after mounting
        await wrapper.vm.$nextTick();

        // Now, the categories should be fetched and the DOM updated
        await wrapper.vm.$nextTick(); // Add another nextTick to ensure the DOM has updated

        expect(wrapper.findAll('.category-list button').length).toBe(mockCategories.length);
        expect(wrapper.text()).toContain(mockCategories[0].name);
        expect(wrapper.text()).toContain(mockCategories[1].name);
    });

    it('fetches and renders books when a category is selected', async () => {
        const wrapper = mount(CategorySearch);

        // Simulate selecting a category
        await wrapper.findAll('.category-list button')[0].trigger('click');
        await wrapper.vm.$nextTick(); // Wait for Vue to process updates

        // Expectations for books
        expect(wrapper.findAll('.book-results li').length).toBe(mockBooks.length);
        expect(wrapper.text()).toContain(mockBooks[0].title);
        expect(wrapper.text()).toContain(mockBooks[0].author);
        expect(wrapper.text()).toContain(`${mockBooks[0].price}€`);
    });
});
