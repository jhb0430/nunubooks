#include<stdio.h>
#include<stdlib.h>
#include<math.h>

//list 구조체 ( 구성 요소 )
typedef int list_element;
typedef struct ListNode {
    list_element data;
    struct ListNode* link;
} ListNode;

ListNode* insert_first(ListNode* head, list_element value);

ListNode* insert_first(ListNode* head, list_element value); // 헤드 노드 생성
ListNode* insert_last(ListNode* head, list_element value); // 마지막 노드 생성
ListNode* insert_sort(ListNode* head, list_element value); // 정렬된 노드 생성
ListNode* delete_first(ListNode* head); // 헤드 노드 삭제
ListNode* delete_last(ListNode* head); // 마지막 노드 삭제
// list의 value 노드 모두 삭제
ListNode* delete_search(ListNode* head, list_element value);
ListNode* search(ListNode* head, list_element value); // value 노드 검색
// 리스트 결합 head1 + head2
ListNode* concat(ListNode* head1, ListNode* head2);
ListNode* reverse(ListNode* head); // 리스트 역순 연결
ListNode* ListSort(ListNode* head); // list 순차 정렬
void print_list(ListNode* head); // 리스트 출력
void freelist(ListNode* head); // 리스트 메모리 해제


int main() {
    //1,3,9
    ListNode* list1 = NULL;

    for (int i = 1; i <= 12; i++)
        if (12 % i == 0)
            list1 = insert_first(list1, i);
    print_list(list1);
    printf("\n\n");
    ListSort(list1);

    list1 = delete_search(list1, 12);
    
    ListNode* list2 = NULL;

    for (int i = 1; i <= 66; i++)
        if (66 % i == 0)
            list2 = insert_first(list2, i);

    print_list(list2);
    printf("\n\n");

    while (list2) {
        list2 = delete_last(list2);
        print_list(list2);
        printf("\n\n");
    }

    freelist(list1);

    printf("\n\n\n");
}

ListNode* insert_first(ListNode* head, list_element value)
{
    ListNode* node = malloc(sizeof(ListNode));//ListNode n1;
    node->data = value;
    node->link = head;

    /* ListNode n1;
     n1.data = 9;
     n1.link = head;*/
    return node;
}

ListNode* insert_last(ListNode* head, list_element value)
{
    ListNode* node = malloc(1 * sizeof(ListNode)); //ListNode n1;
    if (node == NULL)return node;
    node->data = value;
    node->link = NULL;
    //노드가 0개
    if (head == NULL)return node;
    //노드가 1개
    ListNode* tail = head;
    while (tail->link != NULL)
        tail = tail->link;

    tail->link = node;

    return head;
}

ListNode* insert_sort(ListNode* head, list_element value)
{
    ListNode* node = malloc(1 * sizeof(ListNode)); //ListNode n1;
    if (node == NULL)return node;
    node->data = value;
    node->link = NULL;
    if (head == NULL)return node;
    ListNode* F = head, * R = NULL;
    while (F != NULL && F->data <= value) {
        R = F;
        F = F->link;
    }
    if (F == head) {
        node->link = F;
        head = node;

    }
    else {
        R->link = node;
        node->link = F;
    }

    return NULL;
}

void print_list(ListNode* head)
{

    ListNode* p = head;
    while (p) {
        printf("%3d->", p->data);
        p = p->link;
    }
}

ListNode* delete_first(ListNode* head)
{
    if (head == NULL)return NULL;
    ListNode* remove = head;
    head = head->link;
    free(remove);
    return head;
}

ListNode* delete_last(ListNode* head)
{//노드가 0개
    if (head == NULL)return NULL;

    else {
        ListNode* remove = head, * rear = NULL;
        while (remove->link != NULL) {
            rear = remove;
            remove = remove->link;
        }
    if (remove == head)head = NULL;
    else rear->link = NULL;
    free(remove);
    return head;
    }

}


ListNode* delete_search(ListNode* head, list_element value)
{
    ListNode* remove = head;
    ListNode* rear = NULL;
    while (remove != NULL && remove->data != value) {
        rear = remove;
        remove = remove->link;
    }
    if (remove != head) {
        if (remove == head)head = head->link;
        else rear->link = remove->link;
        free(remove);
    }
    return head;



    //삭제할 노드 찾기 
    //삭제할 노드 이전의 위치가 삭제 노드 다음을 가리키도록 수정
    //노드 삭제

    //노드가 0개 
}

#define swap(type, a ,b){type t=a; a=b;b=t;}
ListNode* ListSort(ListNode* head)
{
    for (ListNode* start = head; start != NULL;start = start->link) {
        for (ListNode* cmp = start->link; cmp; cmp = cmp->link) {
            if (start->data > cmp->data)
                swap(list_element, start->data, cmp->data);
        }
    }
    return head;
}

ListNode* search(ListNode* head, list_element value)
{
    for (ListNode* p = head;p;p = p->link) {
        if (p->data == value) return p;
    }
    return NULL;
}

ListNode* concat(ListNode* head1, ListNode* head2)
{
    /*if (head1 == NULL && head2 = NULL)return NULL;*/
    if (head1 == NULL) return head2;
    else if (head2 == NULL) return head1;

    // head1 의 꼬리칸 찾기
    ListNode* tail = head1;
    while (tail->link != NULL)
        tail = tail->link;
    //꼬리칸을 head2로 연결
    tail->link = head2;
    return head1;
}

ListNode* reverse(ListNode* head)
{
    ListNode* f = head, * p = NULL, * r = NULL;
    while (f) {
        r = p;
        p = f;
        f = f->link;
        p->link = r;
    }
    return p;
}

void freelist(ListNode* head)
{
    while (head)
        head = delete_first(head);
    /*  while (head) {
          ListNode* remove = head;
          head = head->link;
          free(remove);*/


}

